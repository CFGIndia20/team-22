<?php

header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
require_once 'Database.php';
require_once 'product.php';

$instance = Database::getInstance();
$db = $instance->getConnection();

$category=new Product($db);
$result=$category->show_Products();
$num=$result->rowCount();

if($num>0) {
    $product_arr=array();
    $product_arr['data']=array();


    while($row=$result->fetch(PDO::FETCH_ASSOC)){
        extract($row);
        $product_item=array(
            'product_id' => $product_id,
            'typeName' => $typeName, 
            'description' => $description
        );
        array_push($product_arr['data'], $product_item);
    }
    echo json_encode($product_arr);
}
else {
    echo json_encode(
        array('message' => 'no products found')
    );
} 



?>