#!/bin/bash
if [ -z "$JAVA_HOME" ]; then
    echo ""
    echo "No JAVA_HOME variable found in your environment!"
    echo ""
    exit 1
fi
JAVALOCAL=$JAVA_HOME/bin/
TAMANDARELOCAL=/opt/tamandare
if [ -z "$TAMANDARELOCAL" ]; then
    echo ""
    echo "No TAMANDARELOCAL variable found in your environment!" 
    echo "TAMANDARELOCAL is the directory of TAMANDARE installation"
    echo "Example: /opt/tamandare"
    echo ""
fi
cd $TAMANDARELOCAL
$JAVALOCAL/java -cp $TAMANDARELOCAL/:$TAMANDARELOCAL/:$TAMANDARELOCAL/jetty-6.1.0.jar:$TAMANDARELOCAL/jetty-util-6.1.0.jar:$TAMANDARELOCAL/serializer.jar:$TAMANDARELOCAL/derby.jar:$TAMANDARELOCAL/derbyclient.jar:$TAMANDARELOCAL/xalan.jar:$TAMANDARELOCAL/jetty-embedded-6.1.5.jar:$TAMANDARELOCAL/xml-apis.jar:$TAMANDARELOCAL/servlet-api-2.5.jar:$TAMANDARELOCAL/xercesImpl.jar:$TAMANDARELOCAL/tamandare.jar  org.jdamico.tamandare.utils.Launch
