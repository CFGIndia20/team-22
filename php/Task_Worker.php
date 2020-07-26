<?php


class Task_Worker
{
    private $conn;
    private $data;
    private $table = "task_worker";
    private $product_Table = "product";
    private $result;
    private $tasks_Table = "tasks";
    private $listWorkers ;
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
//    public function list_of_workers_Manager($worker_id)
//    {
////        return $this->listWorkers;
//    }


    public function get_feedback_details($worker_id)
    {
        $query1 = "SELECT worker_id FROM worker WHERE manager_id = :manager_id";
        $stat = $this->conn->prepare($query1);
        $stat->bindparam(":manager_id", $worker_id);
        $stat->execute();
        while ($row = $stat->fetch(PDO::FETCH_ASSOC)) {
            $query2 = "SELECT task_id , product_id, worker_uploaded_L0,quantity FROM $this->table WHERE worker_id = :worker_id and worker_uploaded_L0 != approved_L1";
            $stat = $this->conn->prepare($query2);
            $stat->bindparam(":worker_id", $row["worker_id"]);
            $stat->execute();
            while ($x = $stat->fetch(PDO::FETCH_ASSOC)) {
//                echo json_encode($x);
//                echo json_encode($temp);
//                $temp = $stat->fetch(PDO::FETCH_ASSOC);
                $row['task_id'] = $x['task_id'];
                $row['quantity'] = $x['quantity'];
                $row['worker_uploaded_L0'] = $x['worker_uploaded_L0'];
                $row['product_id'] = $x['product_id'];
                break;
            }
//            echo json_encode($row);
            $query3 = "SELECT end_date FROM $this->tasks_Table where task_id = :task_id";
            $stat = $this->conn->prepare($query3);
            $stat->bindparam(":task_id", $row["task_id"]);
            $stat->execute();
            $row['end_date'] = $stat->fetch(PDO::FETCH_ASSOC)['end_date'];

            $query4 = "SELECT typeName from $this->product_Table WHERE product_id = :product_id";
            $stat = $this->conn->prepare($query4);
            $stat->bindparam(":product_id", $row["product_id"]);
            $stat->execute();
            $row['typeName'] = $stat->fetch(PDO::FETCH_ASSOC)['typeName'];

            unset($row['product_id']);

            $this->data[] = $row;
            break;
        }
        return $this->data;

    }

}