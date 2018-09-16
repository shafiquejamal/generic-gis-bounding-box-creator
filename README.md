# Generic GIS Bounding Box Creator

This code shows how to create bounding boxes for given GPS coordinates and an edge length in km.

## Use

Given an input file:

```
iD,lat,lon,some other measure
1633,5.579507,-0.220622,29.0
1660,5.662031,0.000939,6.0
1736,5.57131,-0.21518,12.0
...
```



the following command from the `SBT` prompt

```
run /path/to/input-file lat lon 1 /path/to/output-file
```

would produce the following:

```
iD,lat,lon,some other measure,nWLat,nWLng,sWLat,sWLng,nELat,nELng,sELat,sELng
1633,5.579507,-0.220622,29.0,5.583998602476685,-0.22513498391669123,5.575015397523315,-0.22513498391669123,5.583998602476685,-0.21610901608330882,5.575015397523315,-0.21610901608330882
1660,5.662031,0.000939,6.0,5.666522602476685,-0.0035746236835073183,5.6575393975233155,-0.0035746236835073183,5.666522602476685,0.005452623683507319,5.6575393975233155,0.005452623683507319
1736,5.57131,-0.21518,12.0,5.575801602476686,-0.219692920890517,5.566818397523316,-0.219692920890517,5.575801602476686,-0.21066707910948304,5.566818397523316,-0.21066707910948304
...
```

The format of the `SBT` command is:

```
run run /path/to/input-file heading-of-latitude-column heading-of-longitude-column edge-length-in-km /path/to/output-file
```
