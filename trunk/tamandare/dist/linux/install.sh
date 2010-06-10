#!/bin/bash
mkdir /home/"$(whoami)"/.tamandare/
sudo mkdir /etc/tamandare
sudo cp tamandare.conf /etc/tamandare/
sudo cp tamandare.xsd /etc/tamandare/tamandare.xsd
sudo mkdir /var/log/tamandare
sudo touch /var/log/tamandare/tamandare.log
sudo mkdir /opt/derby
sudo echo > "dbpath = /home/`$(whoami)`/.tamandare/db"
echo ""
echo "BEFORE RUN TAMANDARE YOU MUST EDIT /etc/tamandare/tamandare.conf" 
echo ""


