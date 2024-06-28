/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.tasks

import org.gradle.api.tasks.Input
import org.mjdev.gradle.base.BaseTask
import org.mjdev.gradle.extensions.file
import org.mjdev.gradle.extensions.writeText
import org.mjdev.gradle.extensions.mapToString
import java.io.File
import java.util.Date

@Suppress("DEPRECATION")
open class WebServiceCreateTask : BaseTask() {

    @Input
    var domain: String = "mjdev.org"

    @Input
    var servicePort: Int = 0

    @Input
    var serviceName: String = "tvapp"

    @Input
    var serviceVersion: String = "1.0.0"

    @Input
    val serviceAuthor: String = "Milan Jurkulák"

    @Input
    var serviceCompany: String = "mjdev"

    @Input
    var serviceLicense: String = ""

    @Input
    val serviceDescription: String = "$serviceName application website"

    @Input
    val serviceDependencies = mutableMapOf(
        "ejs" to "^3.1.9",
        "express" to "^4.18.2",
        "express-static" to "^1.2.6",
    )

    @Input
    val serviceDevDependencies = mutableMapOf(
        "nodemon" to "^2.0.7",
    )

    private val letsEncryptDir = "/etc/letsencrypt/live"
    private val fullchainPem = "fullchain.pem"
    private val privkeyPem = "privkey.pem"
    private val packageFileName = "package.json"
    private val appName = "app"

    private val systemdFile
        get() = "$serviceName.service"
    private val nginxFileName
        get() = "$serviceName.nginx"
    private val appFileName
        get() = "$appName.js"
    private val serviceUrl
        get() = "http://localhost:$servicePort"
    private val serverUrl
        get() = "$serviceName.$domain"
    private val sslCertificate
        get() = "$letsEncryptDir/$serverUrl/$fullchainPem"
    private val sslCertificateKey
        get() = "$letsEncryptDir/$serverUrl/$privkeyPem"

    private val webDir: File
        get() = project.rootProject.file("web")

    private val nginxFileContent
        get() = "server {\n" +
                "    server_name $serverUrl;\n" +
                "    listen [::]:443 ssl;\n" +
                "    listen 443 ssl;\n" +
                "    ssl_certificate $sslCertificate;\n" +
                "    ssl_certificate_key $sslCertificateKey;\n" +
                "    include /etc/letsencrypt/options-ssl-nginx.conf;\n" +
                "    ssl_dhparam /etc/letsencrypt/ssl-dhparams.pem;\n" +
                "    location / {\n" +
                "        proxy_pass $serviceUrl;\n" +
                "        proxy_http_version 1.1;\n" +
                "        proxy_set_header Upgrade \$http_upgrade;\n" +
                "        proxy_set_header Connection 'upgrade';\n" +
                "        proxy_set_header Host \$host;\n" +
                "        proxy_cache_bypass \$http_upgrade;\n" +
                "    }\n" +
                "}"

    private val packageJsonFile
        get() = "{\n" +
                "  \"name\": \"$serviceName\",\n" +
                "  \"version\": \"$serviceVersion\",\n" +
                "  \"description\": \"$serviceDescription\",\n" +
                "  \"main\": \"$appFileName\",\n" +
                "  \"scripts\": {\n" +
                "    \"start\": \"export PORT=$servicePort && node $appName\",\n" +
                "    \"dev\": \"nodemon\",\n" +
                "    \"build\": \"npm install.sh && npm run build\",\n" +
                "    \"serve\": \"node $appName\",\n" +
                "    \"release\": \"npm install.sh && npm run build && export PORT=$servicePort && node $appName\"\n" +
                "  },\n" +
                "  \"author\": \"$serviceAuthor $serviceCompany (c) ${Date().year}\",\n" +
                "  \"license\": \"$serviceLicense\",\n" +
                "  \"dependencies\": {\n" +
                serviceDependencies.mapToString() +
                "  },\n" +
                "  \"devDependencies\": {\n" +
                serviceDevDependencies.mapToString() +
                "  }\n" +
                "}"

    private val systemdFileContent
        get() = "[Unit]\n" +
                "Description=$serviceName web service in node js\n" +
                "After=syslog.target network.target remote-fs.target nss-lookup.target\n" +
                "\n" +
                "[Service]\n" +
                "Type=simple\n" +
                "PIDFile=/run/$serviceName.pid\n" +
                "WorkingDirectory=/opt/services/$serviceName\n" +
                "ExecStart=\"export PORT=$servicePort && npm run start &\"\n" +
                "ExecReload=/bin/kill -s HUP \$MAINPID\n" +
                "ExecStop=/bin/kill -s QUIT \$MAINPID\n" +
                "PrivateTmp=true\n" +
                "NonBlocking=true\n" +
                "Restart=on-failure\n" +
                "\n" +
                "[Install]\n" +
                "WantedBy=multi-user.target"

    init {
        group = "mjdev"
        description = "This task create web page files, for the project."
        outputs.upToDateWhen { false }
    }

    override fun onClean() {
        webDir.file(systemdFile).delete()
        webDir.file(nginxFileName).delete()
        webDir.file(packageFileName).delete()
    }

    override fun onAssemble() {
        webDir.file(systemdFile).writeText(systemdFileContent)
        webDir.file(nginxFileName).writeText(nginxFileContent)
        webDir.file(packageFileName).writeText(packageJsonFile)
    }
}
