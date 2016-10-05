<?php

 Class Record
 {
     private $dbo;
     
     function __construct($db){
         $this->dbo = $db;
     }
     
     /**
      * Accepts two parameters, one for the inviter, and one for the person
      * invited to record audio.
      * @param type $inviter
      * @param type $invited
      */
     function sendRecordInvite($args)
     {
         $arg = explode(":-", $args);
         $inviter = $arg[0];
         $invited = $arg[1];
         $ubookID = $arg[2];
         
         $pass = $this->generateRandomString(100);
         
         $pass .= $ubookID;
         
         $sql = "INSERT INTO RecordingInvites (recording_invite_id, user_invited, 
                    invite_sent_by, user_book_id, acceptance_status, date_invited)
                 VALUES ('$pass', '$invited', '$inviter', '$ubookID', '0', now()";
         $inviteID = $this->dbo->doInsert($sql);
         
         if($inviteID > 0){
             return true;
         }else {
             return false;
         }
     }
     
     function acceptInvite($recordingInviteID)
     {
         $sql = "UPDATE RecordingInvites
                SET acceptance_status = '1'
                WHERE recording_invite_id = '$recordingInviteID'";
         
         $this->dbo->doQuery($sql);
     }
     
     function recordPage($userPageID, $audioURL)
     {
         $sql = "UPDATE UserPages
                 SET audio_url = '$audioURL'
                 WHERE user_page_id = '$userPageId'";
         $this->dbo->doQuery($sql);
     }
     
     function generateRandomString($length = 10) {
        $characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()_-+=<>?:",./;';
        $randomString = '';
        for ($i = 0; $i < $length; $i++) {
            $randomString .= $characters[rand(0, strlen($characters) - 1)];
        }
        return $randomString;
    }
 }
?>
