@extends('canevas')
@section('title','Commander')
@section('content')
<h2>Selectionner votre pizza</h2>
<label for="pizzas">Choose a Pizza:</label>
  <select name="pizzas" id="allPizzas">
  @foreach ($pizzas as $pizza)
  <option value="{{$pizza}}">{{$pizza}}</option>
  @endforeach
  </select>

  <h2>Ingredients</h2>
    <div id="garnitures">
    @foreach ($garnitures as $garniture)
    <li><input type="checkbox" id="{{$garniture}}" name="scales"
             unchecked>{{$garniture}}</li>
    @endforeach
    <p id="answer"></p>
    <script>
       $.getJSON("/pizzas/{id}/ingredients", function (data, status) {
                     console.log(data);
                    for(let i=0;i<data.length;i++){
                         $("#message").append("<ul><li>"+data[i].author_id+"  : "+"</li>"+"<li>"+data[i].content+"</li>"+"</ul>")
                        
                     }
                     })
    </script>
    </div>
   
@endsection