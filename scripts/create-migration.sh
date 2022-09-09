#!/bin/bash

clojure -M -m patient-samurai.migrations.create $@
