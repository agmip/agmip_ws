<?php include("parts_checkSession.php"); 
include('RestRequest.php');
$path = 'java.open.agmip.org/AgMIPWS2/';
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Language" content="en-US" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="css/frame.css" />
<title>Read Weather Files</title>
<script src="js/function.js" type="text/javascript">
</script>
<style type="text/css">
.style1 {
	border: 1px solid #000000;
	background-color: #C0C0C0;
}
.style2 {
	border: 2px solid #000000;
}
.style3 {
	border: 1px solid #000000;
}
.style4 {
	border: 1px solid #000000;
	text-align: center;
}
.style5 {
	text-align: center;
}
</style>
</head>

<body>
<div id="container">
	<?php include("parts_header.php"); ?>
	<?php include("functionForWth.php"); ?>
	<?php
		$fileNum = 1;
		$files = array();
		
		for($i = 0; $i < $fileNum; $i++) {
			$line = "";
			$flg[0] = "";
			$flg[1] = "";
			$flg[2] = "";
			$lineNo = 0;
			$ret = createWthArray();
			
			
			$file = fopen($_FILES["FilePath"]["tmp_name"],"r") or exit("Unable to open file!");
			
			while(!feof($file)) {
				$lineNo++;
				$line = fgets($file);
				$flg = judgeContentType($line, $flg); // explode,splitStrToArray
				//echo "[line".$lineNo."],[".$flg[0]."],[".$flg[1]."],[".$flg[2]."]<br>"; //debug
				$ret = getSpliterResult($flg, $line, $ret);
			}
			fclose($file);
			
			
			
			//$ret = checkWthId($ret, $_FILES["FilePath"]["name"]); // TODO if there must be a wth id, then add this method to do that
			
			$files[$i] = $ret;
			printWthArray($ret); //debug
			//print_r($ret); //debug
			
			
			
			// TODO The Weather Source has a primary key (WID). I've generated it with an auto-increment in the web service, i don't now if this is right
			$source['fdName'] = $ret['inste'];
			$source['wstaId'] = $ret['inste'].$ret['sitee'];
			$source['site'] = $ret['sitee'];
			$source['lat'] = $ret['xlat'];
			$source['lon'] = $ret['xlong'];
			$source['elev'] = $ret['elev'];
			$source['tav'] = $ret['tav'];
			$source['tamp'] = $ret['tamp'];
			$source['refht'] = $ret['refht'];
			$source['wndht'] = $ret['wndht'];	
			
			$request = new RestRequest($path . 'resources/weatherSources', 'POST', json_encode($source));
			$request->setAcceptType('application/json');
			$request->execute();
	
			$response = $request->getResponseInfo();
	
			if ($response['http_code'] == "201"){
				echo '<strong>Weather Source created</strong><br/><br/>';
			}
			else{
				echo '<span style="color:#ff0000">Error - Weather Source: '.$response['http_code'].'</span><br/>';
			}
			
			$dailies = array();
			
			// Iterate over the daily to build a compatible JSON string
			// TODO Here's the same problem as above. I didn't know what values to use, so I put these
			foreach ($ret['daily'] as $daily){
				$dailyPK['wid'] = 12;
				$dailyPK['wtyr'] = $daily['yrdoyw'];
				$dailyPK['wtday'] = 90;
				
				$day['weatherDailyPK'] = $dailyPK;
				$day['srad'] = $daily['srad'];
				$day['tmax'] = $daily['tmax'];
				$day['tmin'] = $daily['tmin'];
				$day['rain'] = $daily['rain'];
				
				if ($daily['tdew'] != '')
					$day['tdew'] = $daily['tdew'];
					
				if ($daily['windsp'] != '')
					$day['windsp'] = $daily['windsp'];
					
				if ($daily['par'] != '')
					$day['par'] = $daily['par'];
				
				$dailies[] = json_encode($day);
			}
			
			$str_dailies = '['.implode(',', $dailies).']';
			
			$request = new RestRequest($path . 'resources/weatherDailiesService', 'POST', $str_dailies);
			$request->setAcceptType('application/json');
			$request->execute();
	
			$response = $request->getResponseInfo();
	
			if ($response['http_code'] == "201"){
				echo '<strong>Dailies created</strong><br/>';
			}
			else{
				echo '<span style="color:#ff0000">Error - Weather Daily: '.$response['http_code'].'</span><br/>';
			}
		}

	?>
	<div id="content">
		<form id="form1" method="post" action="saveFiles.php">
			<table class="style2" style="width: 600px" align="center">
				<!--tr>
					<td class="style1">Experiment ID</td>
					<td class="style1">Treatment Num</td>
					<td class="style1">Treatment Name</td>
					<td class="style1" style="width: 81px">Select?</td>
				</tr-->
				<?php
					
					echo "<input name='files' type='hidden' value='" . json_encode($files) . "' />";
				?>
			</table>
			<br />
			<p class="style5">
				<span><input name="Button1" type="button" value="Back" onclick="goBack()"/></span>&nbsp;
				<span><input id="save" name="save" type="button" value="Save" onclick="checkChkbox('<?php echo $jsCheck; ?>')" /></span></form>
			</p>
		</form>
	</div>
	<?php include("parts_footer.php"); ?>
</div>
</body>
</html>
