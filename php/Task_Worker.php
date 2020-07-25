<?php


class Task_Worker
{
    private $conn;
    private $data;
    private $table = "task_worker";
    private $product_Table = "product";
    private $result;
    private $tasks_Table = "tasks";

    public function __construct($db)
    {
        $this->conn = $db;
    }

    public function get_worker_task($worker_id)
    {
        $query = "SELECT quantity, product_id, task_id  from $this->table WHERE worker_id = :worker_id";
        $stat = $this->conn->prepare($query);
        $stat->bindparam(":worker_id", $worker_id);
        $stat->execute();
        while ($row = $stat->fetch(PDO::FETCH_ASSOC)) {
            // echo json_       encode($row) ;
            $query2 = "SELECT typeName FROM $this->product_Table where product_id = :product_id";
            $stat = $this->conn->prepare($query2);
            $stat->bindparam(":product_id", $row["product_id"]);
            $stat->execute();
            $row['typeName'] = $stat->fetch(PDO::FETCH_ASSOC)['typeName'];
            unset($row['product_id']);
            $query3 = "SELECT end_date FROM $this->tasks_Table where task_id = :task_id";
            $stat = $this->conn->prepare($query3);
            $stat->bindparam(":task_id", $row["task_id"]);
            $stat->execute();
            $row['end_date'] = $stat->fetch(PDO::FETCH_ASSOC)['end_date'];
            $this->data[] = $row;
        }
        return $this->data;
    }

}