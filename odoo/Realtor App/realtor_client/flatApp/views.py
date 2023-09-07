from django.shortcuts import render
import xmlrpc.client


url="http://localhost:8069"
db="dev01"
user="djenoufrank@gmail.com"
password="odoo"

common = xmlrpc.client.ServerProxy('{}/xmlrpc/2/common'.format(url))
uid = common.authenticate(db, user, password, {})
model = xmlrpc.client.ServerProxy('{}/xmlrpc/2/object'.format(url))

def index(request):
    return render(request, 'flatApp/flats_view.html')


def get_all_flat_object():
    return model.execute_kw(db, uid, password,'realtor.flat', 'search_read', [[]])

def get_all_buyer_object():
    return model.execute_kw(db, uid, password,'res.partner', 'search_read', [[]])

def get_all_stock_object():
    return model.execute_kw(db, uid, password,'stock.inventory.line', 'search_read', [[]])

def get_all_offer_object():
    return model.execute_kw(db, uid, password,'realtor.offer', 'search_read', [[]])

def get_all_product_object():
    return model.execute_kw(db, uid, password,'product.product', 'search_read', [[]])

def is_buyer_exist(name):
    for buyer in get_all_buyer_object():
        if (buyer.get("name")==name):
            return True
    return False

def get_id_buyer(name):
    id=0
    for buyer in get_all_buyer_object():
        if (buyer.get("name")==name):
            id=buyer.get("id") 
    return id

def create_new_buyer(name):
    id=model.execute_kw(db, uid, password, 'res.partner', 'create', [{'name': name}])
    return id

def create_new_offer(flat_id,buyer_id,offer):
    model.execute_kw(db, uid, password, 'realtor.offer', 'create', [{'flat': flat_id,'buyer': buyer_id,'offer': offer}])

def compute_best_offer():
    id=""
    for offer in get_all_offer_object():
        count=0
        for otherOffer in get_all_offer_object():
            if(otherOffer.get("flat")[1]==offer.get("flat")[1]):
                id=otherOffer.get("flat")[0]
                count+=1

        if (count==1):
            new_best_buyer = otherOffer.get("buyer")[1]
            new_best_price = otherOffer.get("offer")
            model.execute_kw(db, uid, password, 'realtor.flat', 'write', [[id],{'bestBuyer': new_best_buyer,'bestPrice': new_best_price}])
        else:
            for otherOffer in get_all_offer_object():
                if(otherOffer.get("flat")[1]==offer.get("flat")[1] and otherOffer.get("offer")>offer.get("offer")):
                    id = otherOffer.get("flat")[0]
                    new_best_buyer = otherOffer.get("buyer")[1]
                    new_best_price= otherOffer.get("offer")
                    model.execute_kw(db, uid, password, 'realtor.flat', 'write', [[id],{'bestBuyer': new_best_buyer,'bestPrice':new_best_price}])

def get_qty_of_flat():
    stock = {}
    for inventory in get_all_stock_object():
        for product in get_all_product_object():
            if(inventory.get("product_id")[0]==product.get("id")):
                stock[product.get("id")]=inventory.get("product_qty")
    return stock
def create_offer(request):
    name=request.POST['name']
    mount = request.POST['mount']
    flat_id = int(request.POST['flatId'])
    result = ""
  
    if(is_buyer_exist(name)):
        result="Vous êtes enregistré comme acheteur"
    else:
        create_new_buyer(name)
        result="Vous avez été enregistré comme acheteur"
    
    buyer_id=get_id_buyer(name)
    isError=False
    try:
        create_new_offer(flat_id,buyer_id,mount)
    except:
        result="error"   

    if isError:
        result="cette offre existe déjà"
    else:
        compute_best_offer() 
        compute_best_offer()      
    
    context = {
        'allFlats':get_all_flat_object(),
        'stocks':get_qty_of_flat(),
        'products':get_all_product_object(), 
    }
    return render(request,'flatApp/flats_view.html',context)

