<?php
class Worker {

    private $conn;
    private $table='worker';
    private $feedback_table = 'feedback' ;
    private $worker_table = 'task_worker' ;
    public $name;
    public $password;
    public $contact;
    public $center_id;
    public $manager_id;

    public function __construct($db) {
        $this->conn=$db;
    }

    public function add_user() {
        $query ='INSERT INTO '. $this->table . '
        SET
           name = :name,
           password = :password,
           contact = :contact,
           center_id = :center_id,
           manager_id = :manager_id';

        $stmt=$this->conn->prepare($query);
        $this->name=htmlspecialchars(strip_tags($this->name));
        $this->password=htmlspecialchars(strip_tags($this->password));
        $this->contact=htmlspecialchars(strip_tags($this->contact));
        $this->center_id=htmlspecialchars(strip_tags($this->center_id));
        $this->manager_id=htmlspecialchars(strip_tags($this->manager_id));

        $stmt->bindParam(':name', $this->name);
        $stmt->bindParam(':password', $this->password);
        $stmt->bindParam(':contact', $this->contact);
        $stmt->bindParam(':center_id', $this->center_id);
        $stmt->bindParam(':manager_id', $this->manager_id);

        if($stmt->execute()) {
            return true;
            }


        //printf("error: %s.\n", $stmt->error);
        return false;

    }


    public function worker_assigned_to_manager() {
        $query='SELECT 
        name,
        worker_id 
        FROM 
        ' . $this->table . ' 
        WHERE 
        manager_id = ?';
        $stmt=$this->conn->prepare($query);
        $stmt->bindParam(1, $this->manager_id);
        $stmt->execute();
        $num=$stmt->rowCount();

    if($num>0) {
        while($row = $stmt->fetch(PDO::FETCH_ASSOC))
            {
                // $this->name[]=$row['name'];
                // $this->worker_id[]=$row['worker_id'];
                $this->data[] = $row;
            }
        }
        /*
        $row=$stmt->fetch(PDO::FETCH_ASSOC);
        $this->name=$row['name'];
    */
    }

    public function takeFeedback($worker_id , $task_id , $typeName , $quantity, $date, $description ) //to be added in level 0
    {
        $imgPath = "test21.jpg" ;
        $query = "INSERT INTO $this->feedback_table (date, image, task_id, worker_id, feedback_description) 
                    VALUES (:date,:imgPath,:task_id,:worker_id, :feedback_description)";
        $stmt=$this->conn->prepare($query);
        $stmt->bindParam(":date", $date);
        $stmt->bindParam(":imgPath", $imgPath);
        $stmt->bindParam(":task_id", $task_id);
        $stmt->bindParam(":worker_id", $worker_id);
        $stmt->bindParam(":feedback_description", $description);
        if($stmt->execute()){

            $query2 = "UPDATE $this->worker_table SET worker_uploaded_L0 = :quantity WHERE task_id = :task_id AND worker_id = :worker_id";
            $stmt=$this->conn->prepare($query2);
            $stmt->bindParam(":task_id", $task_id);
            $stmt->bindParam(":worker_id", $worker_id);
            $stmt->bindParam(":quantity", $quantity);

            if ($stmt->execute()){
                $data = [];
                $data['worker_id'] = $worker_id;
                return $data;
            }
            else{
                return false;
            }
        }
        else {
            return false ;
        }
    }

    public function giveFeedback($worker_id,$flag,$manager_id)
    {
        if ($flag == "0")
        {
            $s= 0 ;
            $query1 = "UPDATE $this->worker_table SET worker_uploaded_L0 = :worker_uploaded_L0 WHERE worker_id = :worker_id" ;
            $stmt=$this->conn->prepare($query1);
            $stmt->bindParam(":worker_uploaded_L0", $s);
            $stmt->bindParam(":worker_id", $worker_id);
            if($stmt->execute())
            {
                $data = [];
                $data['manager_id'] = $manager_id ;
                return $data;
            }
            return false;
        }
        else
        {
            $query2 = "UPDATE $this->worker_table SET approved_L1 = worker_uploaded_L0 WHERE worker_id = :worker_id" ;
            $stmt=$this->conn->prepare($query2);
            $stmt->bindParam(":worker_id", $worker_id);
            if($stmt->execute())
            {
                $data = [];
                $data['manager_id'] = $manager_id ;
                return $data;
            }
            return false;
        }

    }
}



?>
