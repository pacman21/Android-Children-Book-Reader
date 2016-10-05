<?php
    Class Database{
        private $dbh;
        
        function __construct() 
        {
            $db         = "";
            $host       = "localhost";
            $username   = "";
            $password   = "";
            
            $this->dbh = new PDO("mysql:dbname=$db;host=$host", "$username", "$password" );
        }
        
        function doQuery($sql)
        {
            $sth = $this->dbh->prepare($sql);
            $sth->execute();
            /* Fetch all of the remaining rows in the result set */
            $result = $sth->fetchAll(PDO::FETCH_ASSOC);
            return $result;
        }
        
        function doInsert($sql)
        {
            $sth = $this->dbh->prepare($sql);
            $sth->execute();

            $last_id = $this->dbh->lastInsertId();
            return $last_id;
        }
    }
?>
