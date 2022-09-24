@extends('canevas')
@section('title','Pizzas')
@section('content')
<h2>Nos Pizzas</h2>
 <div id="pizzas">
 <ul>
 @foreach ($pizzas as $pizza)
 <li>{{$pizza}}
    <ol>
    @foreach ($ingredients as $pizzaIngredient)
    @if ($pizzaIngredient->pNom == $pizza)
    <li>{{$pizzaIngredient->gNom}} +( {{$pizzaIngredient->gPrix}} €)</li> 
    @endif
    @endforeach
    </ol>
 </li>
 @endforeach
</ul>
 </div>

@endsection