<?php


class ManagerTasks
{
    private $conn ;
    private $data ;
    private $table = "task_manager" ;
    private $product_Table = "product" ;
    private $result ;
    public function __construct($db) {
        $this->conn=$db;
    }

    public function getManagerTasks( $worker_id )
    {
        $query = "SELECT task_id, quantity, product_id  from $this->table WHERE worker_id = :worker_id" ;
        $stat = $this->conn->prepare($query);
        $stat->bindparam(":worker_id" , $worker_id);
        $stat->execute();
        while($row = $stat->fetch(PDO::FETCH_ASSOC))
        {
            echo json_encode($row) ;
            $query2 = "SELECT typeName FROM $this->product_Table where product_id = :product_id" ;
            $stat = $this->conn->prepare($query2);
            $stat->bindparam(":product_id" , $row["product_id"]);
            $stat->execute();
            $row['typeName'] = $stat->fetch(PDO::FETCH_ASSOC)['typeName'];
            unset($row['product_id']);
            $this->data[] = $row;
        }
        return $this->data;
    }



}