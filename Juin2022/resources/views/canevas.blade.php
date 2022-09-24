<!DOCTYPE html>
<html lang="fr">
<head>
<title>@yield('title')</title>
</head>
<style>

      footer {
        margien
        padding: 10px 20px;
        background: #666;
        color: white;
        margin-top: 27em;
      }
      .entete{
  width: 100%;
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  
}
.menu
{
float: right;
width: 200px;
color: white;
background-color: orange;
height : 510px;

}
      
    </style>
<body>
<h1 class ="entete"> Pizza - Acceuil </h1>
<nav class = "menu">

      <li><a  href="http://127.0.0.1:8000/acceuil">Acceuil</a></li>
      <li><a href="http://127.0.0.1:8000/nos-pizzas">Nos pizzas</a></li>
      <li><a href="http://127.0.0.1:8000/commander">Commander</a></li>
</nav>
<main>
 @yield('content')
</main>

</body>
<footer>
  <ul>
      <li>Matricule : 55015</li>
      <li>Nom : Ngoumeni Nkeudjeu Austin</li>
      <li>Acronyme : NRI</li>
  </ul>
</footer>
</html>