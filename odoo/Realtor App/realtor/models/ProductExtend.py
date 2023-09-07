from odoo import models,fields

class ProductExtend(models.Model):    
    _inherit ='product.product'

    type =fields.Selection([('product','1'),('product2','2'),('product','3')],string='Type',default='product')
   