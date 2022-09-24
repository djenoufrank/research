<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\pizzaController;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});
Route::get('/acceuil', [PizzaController::class, 'acceuil']);
Route::get('/nos-pizzas', [PizzaController::class, 'nosPizzas']);
Route::get('/pizzas/{id}/ingredients', [PizzaController::class, 'ingredients']);
Route::get('/commander', [PizzaController::class, 'commander']);
Route::get('/idbypizza/{name}', [PizzaController::class, 'idPizza']);




