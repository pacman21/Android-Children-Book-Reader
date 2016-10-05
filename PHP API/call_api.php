<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
include "Classes/Database.php";
include "Classes/Books.php";
include "Classes/Record.php";
include "Classes/Users.php";

$db = new Database();
    
$function   = (empty($_GET['function']) ? '' : $_GET['function']);
$dateTime   = (empty($_GET['dateTime']) ? '' : $_GET['dateTime']);
$hashCode   = (empty($_GET['hashCode']) ? '' : $_GET['hashCode']);
$object     = (empty($_GET['object']) ? '' : $_GET['object']);

//This is used to verify that the hashes match correctly
$secretStr = "Fq216w2to5PdS5GFl5iL473Tsc08Qa";

//Checks if the request was made within a reasonable time
$diff = time() - $dateTime;
$myHash = md5($secretStr . $dateTime . $function);

if($diff < 120 ){
    if($myHash == $hashCode){
        $arguments = (empty($_GET['arguments']) ? '' : $_GET['arguments']);
        
        $object = new $object($db);
        $ret = $object->$function($arguments);
        echo json_encode($ret);
    }
} else {
    echo "incorrect values $diff";
}
?>
