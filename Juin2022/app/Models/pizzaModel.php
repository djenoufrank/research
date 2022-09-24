<?php
 namespace App\Models;
 use PDO;
 use \Illuminate\Support\Facades\DB;


 class pizzaModel{
    public static function allPizzas() {
       
        $pdo = \Illuminate\Support\Facades\DB::getPdo();
        $rs = $pdo->query("select pNom from pizza")->fetchAll(PDO::FETCH_COLUMN);
      
        return  $rs;
     }
     public static function allPizzasGarnitures() {
       
      $rs= DB::table('contient')
      ->join('pizza', 'contient.cPizza', '=', 'pizza.pId')
      ->join('garniture', 'contient.cGar', '=', 'garniture.gId')
    ->select('pizza.pNom','garniture.gNom','garniture.gPrix')
    ->get();
      //$rs = $pdo->query("select p.pNom,g.gNom from contient c JOIN pizza p on c.cPizza=p.pId JOIN garniture g on c.cGar=g.gId")->fetchAll(PDO::FETCH_COLUMN);
      return  $rs;
   }
     public static function ingredients($id) {
       
        $pdo = \Illuminate\Support\Facades\DB::getPdo();
        $rs = $pdo->query("select gId,gNom,gPrix,cPizza,cGar from garniture join contient
        on contient.cGar=garniture.gId
         where contient.cPizza = '$id'")->fetchAll(PDO::FETCH_ASSOC);
        $result=json_encode($rs,JSON_UNESCAPED_UNICODE );
        return $result;
     }

     public static function allGarnitures() {
        $pdo = \Illuminate\Support\Facades\DB::getPdo();
        $rs = $pdo->query("select gNom from garniture")->fetchAll(PDO::FETCH_COLUMN);
            
        return  $rs;
     }
     public static function allPrice(){
        $pdo = \Illuminate\Support\Facades\DB::getPdo();
        $rs = $pdo->query("select gPrix from garniture")->fetchAll(PDO::FETCH_COLUMN);
            
        return  $rs;
     }
     public static function idPizza($name){
        $pdo = \Illuminate\Support\Facades\DB::getPdo();
        $rs = $pdo->query("select pId from pizza where pNom = '$name'")->fetchAll(PDO::FETCH_COLUMN);
            
        return  $rs;
     }
    
        
 }
 ?>