# Accessing the Web Service through the PHP tool #

The AgMIP Web Service allows data sending through the PHP tool, in JSON format, only making a few changes in the array keys.
At the moment, the Web Service supports directly sending from the PHP tool of the soil and weather files. The experimental files can be sent in XML or JSON formats, previously converted, through a HTTP request or from the [Web Service Interface](http://java.open.agmip.org/AgMIPWS).


## Weather Files ##

Simply log in the web tool, select a file and click on the Read button. This repository has a [PHP sample file](http://github.com/fonini/agmip_ws/php-samples/readWthFiles.php) modified to send properly JSON data to the web service. We must send a Weather Source JSON object in the first request, then we send a Weather Daily JSON array.

  * Weather Source JSON example
  
        [php]
        $source = '{"fdName":"AL","site":"CL","lat":32.48,"lon":-86.38,"elev":185,"tav":16.5,"tamp":10.3,"refht":1,"wndht":1}';


  * Weather Daily JSON example
  
        [php]
        $daily = '[{"weatherDailyPK":{"wid":12,"wtyr":56001,"wtday":90},"srad":8.2,"tmax":9,"tmin":-9,"rain":0},{"weatherDailyPK":{"wid":12,"wtyr":56002,"wtday":90},"srad":12.9,"tmax":14,"tmin":-8,"rain":0}]';
        

## Soil Files ##

Execute the same procedure as the weather files. The PHP sample is located [here](http://github.com/fonini/agmip_ws/php-samples/readSoilFiles.php). This PHP file sends only one request to the web service, a Soil Profile JSON array of objects, with a collection of Soil Profile Layers inside each Soil Profile object.

  * Weather Source JSON example
  
        [php]
        $soil = '[{"people":"Fl10151022","site":"Pasco","soillat":28.384,"soillong":-82.398,"salb":-99,"slnf":-99,"smhb":"-99","smpx":"-99","smke":"-99",
        "soilProfileLayerCollection":[
        	{"soilProfileLayerPK":{"soilId":"aa","soilFileId":"aa","sllb":2},"slmh":"-99","slll":0,"sldul":4,"slsat":-99,"slcec":6,"sadc":-99},
        	{"soilProfileLayerPK":{"soilId":"aa","soilFileId":"aa","sllb":3},"slmh":"-99","slll":0,"sldul":4,"slsat":-99,"slcec":3.8,"sadc":-99},
        	{"soilProfileLayerPK":{"soilId":"aa","soilFileId":"aa","sllb":4},"slmh":"-99","slll":0,"sldul":4,"slsat":-99,"slcec":2,"sadc":-99},
        	{"soilProfileLayerPK":{"soilId":"aa","soilFileId":"aa","sllb":5},"slmh":"-99","slll":0,"sldul":4,"slsat":-99,"slcec":1.9,"sadc":-99},
        	{"soilProfileLayerPK":{"soilId":"aa","soilFileId":"aa","sllb":6},"slmh":"-99","slll":0,"sldul":4,"slsat":-99,"slcec":1.8,"sadc":-99},
        	{"soilProfileLayerPK":{"soilId":"aa","soilFileId":"aa","sllb":7},"slmh":"-99","slll":0,"sldul":4,"slsat":-99,"slcec":1.2,"sadc":-99}
        ]}]';
        
