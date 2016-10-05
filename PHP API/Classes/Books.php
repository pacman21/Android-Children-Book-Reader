<?php
    Class Books
    {
        //Object of the database class
        private $dbo;
        //Strings and Ints set for the book object
        private $bookID;
        private $title;
        private $pageCount;
        private $currentCost;
        private $description;
        //Object of the publisher class
        private $publisher;
        public $error;
        
        function __construct($db)
        {
            $this->dbo = $db;
        }
        
        function setBookID($extBookID)
        {
            $this->bookID = $extBookID;
        }
        function getBookID()
        {
            return $this->bookID;
        }
        
        function setTitle($extTitle)
        {
            $this->title = $extTitle;
        }
        
        function getTitle(){
            return $this->title;
        }
        
        function setCost($extCost)
        {
            $this->currentCost = $extCost;
        }
        
        function getCost(){
            return $this->currentCost;
        }
        
        function setDescription($extDesc)
        {
            $this->description = $extDesc;
        }
        
        function getDescription()
        {
            return $this->description;
        }
        
        function setPageCount($extPageCount)
        {
            $this->pageCount = $extPageCount;
        }
        
        function getPageCount()
        {
            return $this->pageCount;
        }
        
        function createBook($extTitle, $extPageCount, $extCost, $extDesc)
        {
            $this->setTitle($extTitle);
            $this->setPageCount($extPageCount);
            $this->setCost($extCost);
            $this->setDescription($extDesc);
            
            $title          = $this->getTitle();
            $pageCount      = $this->getPageCount();
            $cost           = $this->getCost();
            $description    = $this->getDescription();
            $publisherID    = 0;
            //$publisherID    = $this->publisher->getPublisherID();
            
            $sql = "INSERT INTO Books (title, description, page_count, publisher_id, status)
                    VALUES ('$title', '$description', '$pageCount', '$publisherID', '1');";
            
            $id = $this->dbo->doInsert($sql);
            
            if($id > 0){
                $this->setBookID($id);
                return true;
            }else{
                return false;
            }
        }
        
        function addBookPages($page_num, $page_url)
        {
            $book_id = $this->getBookID();
            
            $sql = "INSERT INTO BookPages (book_page_id, book_id, page_num, page_url)
                    VALUES ('$book_id-$page_num', $book_id', '$page_num', '$page_url');";
            
            $id = $this->dbo->doInsert($sql);
            
            if($id > 0){
                return true;
            }else{
                return false;
            }
        }
        
        function getOwnedBooks($userID)
        {
            $sql = "select ub.user_book_id, b.book_id, b.title, b.description, 
                        b.book_cover, b.current_cost, b.page_count
                    From  UserBooks ub, Books b
                    WHERE ub.book_id = b.book_id
                    AND user_id = '$userID'";
            
            return $this->dbo->doQuery($sql);
        }
        
        function getAvailableBooks($userID)
        {
            $sql = "select b.book_id, b.title, b.description, b.book_cover, b.current_cost, b.page_count
                    From  Books b
                    WHERE b.book_id NOT IN (SELECT book_id FROM UserBooks WHERE user_id = '$userID')
                    AND b.status = 1";
            return $this->dbo->doQuery($sql);
        }
        
        function buyBook($args)
        {
            $arg = explode(":-", $args);
            
            $bookID = $arg[0];
            $userID = $arg[1];
            
            $costSQL = "SELECT current_cost 
                        FROM Books
                        WHERE book_id = '$bookID'
                        LIMIT 1";
            $costRes = $this->dbo->doQuery($costSQL);
            
            if(isset($costRes[0]['current_cost'])){
                $cost = $costRes[0]['current_cost'];
                $sql = "INSERT INTO UserBooks (user_id, book_id, date_purchased, purchased_cost)
                        VALUES ('$userID', '$bookID', now(), '$cost')";
                $id = $this->dbo->doInsert($sql);
                
                if($id > 0){
                    $sql = "INSERT INTO UserPages (user_page_id, book_page_id, user_id)
                            SELECT concat(book_page_id, '-', '$userID'), book_page_id,
                                '$userID'
                            FROM BookPages
                            WHERE book_id = '$bookID'";
                    $this->dbo->doInsert($sql);
                    return "true";
                }
            } else {
                $this->error = "No Cost Found For That Book ID";
                return "false";
            }
        }
    }
?>