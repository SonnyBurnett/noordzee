#!/bin/bash

sed -i '' '/location/d' ./texel.xml
sed -i '' '/reference/d' ./texel.xml
sed -i '' '/timezone/d' ./texel.xml
sed -i '' '/source/d' ./texel.xml
sed -i '' '/values/d' ./texel.xml
sed -i -e 's/astronomical-tide/waterstanden/g' ./texel.xml

sed -i '' '/location/d' ./terschelling.xml
sed -i '' '/reference/d' ./terschelling.xml
sed -i '' '/timezone/d' ./terschelling.xml
sed -i '' '/source/d' ./terschelling.xml
sed -i '' '/values/d' ./terschelling.xml
sed -i -e 's/astronomical-tide/waterstanden/g' ./terschelling.xml

sed -i '' '/location/d' ./scheveningen.xml
sed -i '' '/reference/d' ./scheveningen.xml
sed -i '' '/timezone/d' ./scheveningen.xml
sed -i '' '/source/d' ./scheveningen.xml
sed -i '' '/values/d' ./scheveningen.xml
sed -i -e 's/astronomical-tide/waterstanden/g' ./scheveningen.xml

sed -i '' '/location/d' ./ijmuiden.xml
sed -i '' '/reference/d' ./ijmuiden.xml
sed -i '' '/timezone/d' ./ijmuiden.xml
sed -i '' '/source/d' ./ijmuiden.xml
sed -i '' '/values/d' ./ijmuiden.xml
sed -i -e 's/astronomical-tide/waterstanden/g' ./ijmuiden.xml

sed -i '' '/location/d' ./hoekvanholland.xml
sed -i '' '/reference/d' ./hoekvanholland.xml
sed -i '' '/timezone/d' ./hoekvanholland.xml
sed -i '' '/source/d' ./hoekvanholland.xml
sed -i '' '/values/d' ./hoekvanholland.xml
sed -i -e 's/astronomical-tide/waterstanden/g' ./hoekvanholland.xml

sed -i '' '/location/d' ./denhelder.xml
sed -i '' '/reference/d' ./denhelder.xml
sed -i '' '/timezone/d' ./denhelder.xml
sed -i '' '/source/d' ./denhelder.xml
sed -i '' '/values/d' ./denhelder.xml
sed -i -e 's/astronomical-tide/waterstanden/g' ./denhelder.xml

exit 0

