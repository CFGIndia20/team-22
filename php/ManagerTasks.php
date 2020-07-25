<?php

class ManagerTasks
{
    private $conn ;
    private $data ;
    private $table = "task_manager" ;
    private $product_Table = "product" ;
    private $tasks_Table = "tasks" ;
    private $tasks_workerTable = "task_worker" ;
    private $result ;
    private $product_id ;
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
//            echo json_encode($row) ;

            $query2 = "SELECT typeName FROM $this->product_Table where product_id = :product_id";
            $stat = $this->conn->prepare($query2);
            $stat->bindparam(":product_id" , $row["product_id"]);
            $stat->execute();
            $row['typeName'] = $stat->fetch(PDO::FETCH_ASSOC)['typeName'];
            unset($row['product_id']);
            $query3 = "SELECT end_date FROM $this->tasks_Table where task_id = :task_id" ;
            $stat = $this->conn->prepare($query3);
            $stat->bindparam(":task_id" , $row["task_id"]);
            $stat->execute();
            $row['end_date'] = $stat->fetch(PDO::FETCH_ASSOC)['end_date'];
            $this->data[] = $row;
        }
        return $this->data;
    }

    public function assignWorker($worker_id,$manager_id,$task_id,$typeName,$quantity)
    {
        $query1= "SELECT product_id FROM $this->product_Table WHERE typeName = :typeName ";
        $stat = $this->conn->prepare($query1);
        $stat->bindparam(":typeName" , $typeName );
        $stat->execute();
        $product_id = $stat->fetch(PDO::FETCH_ASSOC)['product_id'] ;
//        echo json_encode($this->product_id);
        $L1=0;
        $L2=0;
        $L3=0;

        $query2 = "INSERT INTO $this->tasks_workerTable (task_id, worker_id, product_id, quantity, worker_uploaded_l0, approved_l1, done_l2) 
                VALUES(:task_id,:worker_id, :product_id, :quantity, :I1,:I2,:I3)" ;
        $stat = $this->conn->prepare($query2);
        $stat->bindparam(":task_id" , $task_id );
        $stat->bindparam(":worker_id" , $worker_id );
        $stat->bindparam(":product_id" , $product_id );
        $stat->bindparam(":quantity" , $quantity );
        $stat->bindparam(":I1" , $L1 );
        $stat->bindparam(":I2" , $L2);
        $stat->bindparam(":I3" , $L3 );
        $stat->execute();

        if($stat->execute()) {
            return $manager_id;
        }

        return false;
    }



}