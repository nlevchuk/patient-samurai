#!/bin/bash

echo "Clean directories (target/, classes/)"
rm -rf target/ classes/

echo "Build ClojureScript files"
clojure -M:prod-client

# https://github.com/tonsky/uberdeps#creating-an-executable-jar
echo "Two extra steps to create executable jar"
mkdir classes

clojure -M --eval "(compile 'patient-samurai.server)"
#================

echo "Pack application into jar"
clojure -M:package

echo "Done"
