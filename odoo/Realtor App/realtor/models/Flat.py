from odoo import models,fields,api,exceptions


class Flat(models.Model):
    _name='realtor.flat'

    name=fields.Char(string="nom",required=True)
    description=fields.Text(string="description")
    image = fields.Binary('Image')
    disponibilityDate = fields.Date(string="date de disponibilité")
    price = fields.Float(string="prix",min=1, required=True)
    flatSurface = fields.Integer(string="surface appartement")
    terraceSurface = fields.Integer(string="surface de la terasse", default=0)
    totalSurface = fields.Integer(string="surface totale",compute='calculate_total_surface')
    bestBuyer = fields.Char(string="Meilleur acheteur")
    bestPrice = fields.Float(string="meilleur offre")
    product_id = fields.Many2one('product.template', string='')
    product_ids = fields.One2many('product.template','flat')
    provider = fields.Many2one('res.partner',string="fournisseur")
    
    _sql_constraints = [('unique_name','UNIQUE(name)',"The name must be unique"),]

    @api.constrains('price')
    def check_price_greater_than_zero(self):
        for flat in self:
            if flat.price<=0:
                raise exceptions.ValidationError("the price must be greater than 0")

    @api.constrains('flatSurface')
    def check_flatSurface_greater_than_zero(self):
        for flat in self:
            if flat.flatSurface<=0:
                raise exceptions.ValidationError("the flat surface must be greater than 0")

    @api.constrains('terraceSurface')
    def check_terrace_surface(self):
        for flat in self:
            if flat.terraceSurface<0:
                raise exceptions.ValidationError("the flat surface must be greater than or equal 0")

    @api.depends('flatSurface','terraceSurface')
    def calculate_total_surface(self):
        for flat in self:
            flat.totalSurface= flat.flatSurface + flat.terraceSurface
    
    @api.constrains('bestPrice')
    def check_best_price(self):
        for flat in self:
            if flat.bestPrice<0.9*flat.price:
                raise exceptions.ValidationError("the best price must be greater than or equal 90%")

