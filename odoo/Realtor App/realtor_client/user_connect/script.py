import xmlrpc.client


print(" CONNECTION AU SERVEUR ODOO ")
url="http://localhost:8069"
db="odoo"
username=input("entrez votre nom d'utilisateur : ")
password=input("entrez votre mot de passe : ")
print("connection en cours....")

common = xmlrpc.client.ServerProxy('{}/xmlrpc/2/common'.format(url))
print("Version : ", common.version())

try:
    uid = common.authenticate(db, username, password, {})
    models = xmlrpc.client.ServerProxy('{}/xmlrpc/2/object'.format(url))
    hasRight = models.execute_kw(db, uid, password,'realtor.flat', 'check_access_rights',['read'], {'raise_exception': False})
    if hasRight:
        print("connection réussie")
        print(" *** Lecture des données depuis le serveur d'ODOO*** ")
        flats = models.execute_kw(db, uid, password,'realtor.flat', 'search_read', [[]])
        print("Nombre d'appartements : ", len(flats))
        print("---------------------------------------------------")
        for flat in flats:
            print("nom : ", flat.get("name"))
            print("description : ", flat.get("description"))
            print("prix : ", flat.get("price"))
            print("surface totale : ", flat.get("totalSurface"))
            print("---------------------------------------------------")
except:
    print("Attention les informations fournies sont incorrectes")

