from django.shortcuts import render
from .Forms import UserForm
from .models import User
import xmlrpc.client
from flatApp import views

flats =""

def index(request):
    context = {'form': UserForm, } 
    return render(request, 'user_connect/index.html', context)

def result_connect(request):
    url="http://localhost:8069"
    db="dev01"
    name=request.POST['name'], 
    password = request.POST['password']

    form = UserForm(request.POST) 
    if form.is_valid(): 
        User.objects.create( 
            name=form.cleaned_data['name'], 
            password=form.cleaned_data['password'] 
        )

    try:
        common = xmlrpc.client.ServerProxy('{}/xmlrpc/2/common'.format(url))
        uid = common.authenticate(db,name, password, {})
        models = xmlrpc.client.ServerProxy('{}/xmlrpc/2/object'.format(url))
        hasRight = models.execute_kw(db, uid, password,'realtor.flat', 'check_access_rights',['read'], {'raise_exception': False})
    except:
        answer="false"
        context = {
            'result':answer,
        }
        return render(request, 'user_connect/answer_connect.html', context)
    
    if hasRight:
        global flats
        answer="true"
        flats = models.execute_kw(db, uid, password,'realtor.flat', 'search_read', [[]])
        context = {
            'result':answer,
            'allFlats' : flats,
        }
        return render(request, 'user_connect/answer_connect.html', context)
   
def realtor(request):
    context = {
        'allFlats' : flats,
        'stocks':views.get_qty_of_flat(),
        'products':views.get_all_product_object(), 
        'result':" ",
    }
    return render(request, 'flatApp/flats_view.html', context)
        

    