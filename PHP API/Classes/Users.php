<?php
    Class Books
    {
        //Object of the database class
        private $dbo;
        //Strings and Ints set for the user object
        private $userID;
        
        function __contruct($db)
        {
            $this->dbo = $db;
        }
        
        function setUserID($extUserID)
        {
            $this->userID = $extUserID;
        }
        function getUserID()
        {
            return $this->userID;
        }
        
        //This is not set in here because the email and password will be hashed 
        //And salted before being sent to this function.
        function createUser($email, $password, $salt)
        {
            $sql = "INSERT INTO users (email, password, salt)
                    VALUES ('$email', '$password', '$salt')";
            $user_id = $this->dbo->doInsert($sql);
            
            if($user_id > 0){
                $this->setUserID($user_id);
                return $user_id;
            } else {
                return false;
            }
        }
    }
?>