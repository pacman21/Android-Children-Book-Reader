<?php
    error_reporting(E_ALL);
    ini_set('display_errors', 1);
    
    include "Classes/Database.php";
    include "Classes/Books.php";
    
    $uploadPDF        = !empty($_POST['uploadPDF']) ?  addslashes ($_POST['uploadPDF']) : '';
    $bookTitle        = !empty($_POST['book_title']) ?  addslashes ($_POST['book_title']) : '';
    $bookCost         = !empty($_POST['book_cost']) ?  addslashes ($_POST['book_cost']) : '';
    $bookDesc        = !empty($_POST['book_desc']) ?  addslashes ($_POST['book_desc']) : '';

    echo "<html>
            <form method='Post' enctype='multipart/form-data'> 
                <div class='form_field'>
                    <label for='book'>Upload Book PDF " . ini_get("upload_max_filesize") . ":</label>
                    <input name='books' id='books' type='file' />
                </div>
                <div class='form_field'>
                    <label for='title'>Book Title</label>
                    <input name='book_title' id='title' type='text' />
                </div>
                <div class='form_field'>
                    <label for='book_cost'>Book Cost</label>
                    <input name='book_cost' id='book_cost' type='text' />
                </div>
                <div class='form_field'>
                    <label for='book_desc'>Book Description</label>
                    <input name='book_desc' id='book_desc' type='text' />
                </div>
                <div class='form_field'>
                    <input type='submit' id='uploadPDF' name='uploadPDF' value='Upload PDF' />
                </div>
            </form>
          </html>";
          
    if($uploadPDF == "Upload PDF"){
        $db = new Database();
        if(!empty($book_title)){
            $books = new Books($db);
            
            //Page count will be updated later
            $pageCount = 0;
            $books->createBook($bookTitle, $pageCount, $bookCost, $bookDesc);
            $book_id = $books->getBookID();
        }
        
        $mt = microtime();
        $mt = str_replace(" ", "", $mt);
        $mt = str_replace(".", "", $mt);
        
        $target = "Original/" . $mt . $_FILES['books']['name']; 
        
        if ($_FILES["books"]["error"] > 0 && $book_id !== false)
        {
          echo "Error: " . $_FILES["books"]["error"] . "<br>";
        } else if(move_uploaded_file($_FILES["books"]["tmp_name"], $target)) { 
            $pdf_file = $target;
            $i = 1;

            $boo = true;

            do{
                $output = "Books/$book_id-$i.jpg";
                
                //$outval = exec("gs -sDEVICE=pdfwrite -dNOPAUSE -dBATCH -dSAFER -dFirstPage=$i -dLastPage=$i -sOutputFile=$output $pdf_file");
                $outval = exec("gs -sDEVICE=jpeg -dNOPAUSE -dBATCH -dSAFER -dFirstPage=$i -dLastPage=$i -r300 -sOutputFile=$output $pdf_file");
                
                if(strpos($outval, "No pages will be processed") > 0){
                    echo "$i pages were processed";
                    
                    $i = $i - 1;
                    $db->doQuery("UPDATE Books SET page_count = '$i'
                                    WHERE book_id = '$book_id'");
                                    
                    $boo = false;
                } else{
                    //Accepts page number and page url
                    $books->addBookPages($i, $output);
                    $i = $i + 1;
                }
            }while($boo == true && $i <= 500);
        } else{
            echo "Error Moving the PDF";
        }
    }

?>