<?php
class Product{
private $conn;
private $table='product';

public $product_id;
public $typeName;
public $description;

public function __construct($db) {
    $this->conn=$db;
}

public function show_products() {
    $query='SELECT 
    product_id, 
    typeName,
    description 
    FROM 
    ' . $this->table ;

    $stmt=$this->conn->prepare($query);

    $stmt->execute();
    return $stmt;
}

}
?>

