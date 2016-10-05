<?php
// Where the file is going to be placed 
$target_path = "uploads/";

$book_id = $_POST['book_id'];
$page_num = $_POST['page_num'];
$user_id  = $_POST['user_id'];

if(!isset($book_id)){
    echo "Book id not set";
    return;
} else {
    $fileName = $book_id . "-" . $page_num . "-" . $user_id;
}

/* Add the original filename to our target path.  
Result is "uploads/filename.extension" */
$target_path = $target_path . $fileName . ".3gp"; 

file_put_contents("test.txt", "test: " . date("Y/m/d H:i:s") . var_dump($_FILES));

if(move_uploaded_file($_FILES['form_file']['tmp_name'], $target_path)) {
    echo "The file ".  $fileName . 
    " has been uploaded";
    chmod ($target_path, 0644);
} else{
    file_put_contents("test.txt", "  ERRORRR: " . $_FILES["form_file"]["error"], FILE_APPEND);
    
    echo "There was an error uploading the file, please try again!";
    echo "filename: " .  basename( $_FILES['form_file']['name']);
    echo "target_path: " .$target_path;
}
?>