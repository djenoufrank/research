<?php
 namespace App\Http\Controllers;
 use App\Models\pizzaModel;

 class pizzaController extends Controller {
    public function acceuil() {
         
         return view('acceuil');
     }
     public function nosPizzas() {
         $pizzas=pizzaModel::allPizzas();
         $ingredients=pizzaModel::allPizzasGarnitures();
        return view('nosPizzas', compact('pizzas','ingredients'));
     }
     public function ingredients($id) {
        $result=pizzaModel::ingredients($id);
      echo $result;
    }
    public function commander() {
        $result=pizzaModel::allPizzas();
        $garnitures=pizzaModel::allGarnitures();
        return view('commander',['pizzas'=>$result,'garnitures'=>$garnitures]);
    }
   
    
    
 }