#!/bin/bash
mkdir /home/"$(whoami)"/.tamandare/
sudo mkdir /etc/tamandare
sudo cp tamandare.conf /etc/tamandare/
sudo cp tamandare.xsd /etc/tamandare/tamandare.xsd
sudo mkdir /var/log/tamandare
sudo touch /var/log/tamandare/tamandare.log
sudo mkdir /opt/derby
	# call script to create db http://code.google.com/p/tamandare/wiki/DB_structure

