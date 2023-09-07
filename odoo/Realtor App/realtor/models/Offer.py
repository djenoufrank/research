from odoo import models,fields,api,exceptions


class Offer(models.Model):
    _name='realtor.offer'

    flat = fields.Many2one('realtor.flat' , string = "Flat",required=True)
    buyer = fields.Many2one('res.partner', string = "Acheteur",required=True)
    offer=fields.Float(string = "Offre",required=True)

    _sql_constraints = [('beUnique','UNIQUE(offer)',"offer must be unique"),]

    @api.constrains('offer')
    def check_offer(self):
        for offre in self:
            if offre.offer<0.9*offre.flat.price:
                raise exceptions.ValidationError("the offer must be greater than or equal 90%")

    @api.onchange('offer')
    def compute_best_buyer(self):
        for oneOffer in self:
            conserve=oneOffer
            maxbuyer=""  
            maxprice=""
            for indexOffer in self:
                if(indexOffer.flat.name==conserve.flat.name and indexOffer.offer>=oneOffer.offer):
                    maxbuyer=indexOffer.buyer  
                    maxprice=indexOffer.offer
                    conserve.flat.bestBuyer=maxbuyer.name
                    conserve.flat.bestPrice=maxprice
      

