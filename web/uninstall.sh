#!/bin/sh
#
#  Copyright (c) Milan Jurkulák 2024.
#  Contact:
#  e: mimoccc@gmail.com
#  e: mj@mjdev.org
#  w: https://mjdev.org
#
service_name="app"
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