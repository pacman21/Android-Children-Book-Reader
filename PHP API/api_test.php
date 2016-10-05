<html>
    <body>
        <?php
            $function = "getAvailableBooks";
            $secretStr = "Fq216w2to5PdS5GFl5iL473Tsc08Qa";
            $hash = md5($secretStr . time() . $function);
            $object = "books";
            $arguments = "1";
            
            echo "<form method='get' action='call_api.php'>
                <input type='text' name='function' placeholder='function' value='getAvailableBooks'/>
                <input type='text' name='dateTime' value='" . time() . "'/>
                <input type='text' name='hashCode' value='" . $hash . "'/>
                <input type='text' name='object' value='" . $object . "'/>
                <input type='text' name='arguments' value='" . $arguments . "'/>
                <input type='submit' />
            </form>";
        ?>
    </body>
</html>