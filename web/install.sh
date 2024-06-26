#!/bin/sh
#
#  Copyright (c) Milan Jurkulák 2024.
#  Contact:
#  e: mimoccc@gmail.com
#  e: mj@mjdev.org
#  w: https://mjdev.org
#
service_name="app"
# store actual dir
pwd=$(pwd)
# sw needed
sudo apt-get install.sh certbot nginx-full nodejs npm ufw yarn
# stop service
sudo systemctl stop "service-$service_name"
sudo systemctl disable "service-$service_name"
# remove old
sudo rm -rf "/etc/systemd/system/service-$service_name.service"
sudo rm -rf "/etc/nginx/sites-available/service-$service_name.nginx"
sudo rm -rf "/etc/nginx/sites-enabled/service-$service_name.nginx"
# reload services
sudo systemctl daemon-reload
sudo systemctl reload nginx
# copy things
sudo mkdir -p "/opt/services/$service_name"
sudo cp -rf * "/opt/services/$service_name"
# link things
sudo ln -s "/opt/services/$service_name/$service_name.service" "/etc/systemd/system/service-$service_name.service"
sudo ln -s "/opt/services/$service_name/$service_name.nginx" "/etc/nginx/sites-available/service-$service_name.nginx"
sudo ln -s "/opt/services/$service_name/$service_name.nginx" "/etc/nginx/sites-enabled/service-$service_name.nginx"
# go to directory
cd "/opt/services/$service_name" || exit
# build
sudo npm install.sh
sudo npm audit fix
sudo npm upgrade
#sudo npm run build
# go back
cd "$pwd" || exit
# start services
sudo systemctl daemon-reload
sudo systemctl enable "service-$service_name"
sudo systemctl start "service-$service_name"
sudo systemctl reload nginx