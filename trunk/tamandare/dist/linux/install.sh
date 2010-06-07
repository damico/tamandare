#!/bin/bash
mkdir /home/"$(whoami)"/.tamandare/
sudo mkdir /etc/tamandare
sudo cp tamandare.conf /etc/tamandare/
sudo cp tamandare.xsd /etc/tamandare/tamandare.xsd
sudo mkdir /var/log/tamandare
sudo touch /var/log/tamandare/tamandare.log
# call script to create db
sudo mkdir /opt/derby

