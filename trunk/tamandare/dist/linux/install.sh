#!/bin/bash
mkdir /home/"$(whoami)"/.tamandare/
sudo chmod 777 -R /home/"$(whoami)"/.tamandare/
sudo mkdir /etc/tamandare
sudo cp tamandare.conf /etc/tamandare/
sudo cp tamandare.xsd /etc/tamandare/tamandare.xsd
sudo mkdir /var/log/tamandare
sudo touch /var/log/tamandare/tamandare.log
sudo chmod 777 /var/log/tamandare/tamandare.log
sudo mkdir /opt/derby
sudo cp tamandare /etc/init.d/tamandare
sudo chmod 755 /etc/init.d/tamandare
sudo chmod 755 startTamandareAgents.sh
sudo chmod 755 stopTamandareAgents.sh
sudo touch derby.log
sudo chmod 777 derby.log
echo "dbpath = /home/$(whoami)/.tamandare/db" | sudo tee -a /etc/tamandare/tamandare.conf
echo "whoami = $(whoami)-$(hostname)" | sudo tee -a /etc/tamandare/tamandare.conf
sudo update-rc.d tamandare defaults
echo ""
echo "BEFORE RUN TAMANDARE YOU MUST EDIT /etc/tamandare/tamandare.conf" 
echo ""


