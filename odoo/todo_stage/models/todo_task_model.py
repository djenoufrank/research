from odoo import models,fields,api,exceptions
import logging

_logger = logging.getLogger(__name__)
class TodoTask(models.Model):
    _inherit='todo.task'

    effort_estimate=fields.Integer(string="Estimated effort")
    tag_ids = fields.Many2many('todo.task.tag', string='Tags')

    state = fields.Selection([('draft', 'New'), ('open', 'Started'),
                              ('done', 'Closed')], default='draft')
    
    desc = fields.Text('Description')
    docs = fields.Html('Documentation')

    date_created = fields.Datetime('Create Date and Time',
                    default=lambda self: fields.Datetime.now())
    
    image = fields.Binary('Image')

    @api.constrains('name')
    def _check_size_name(self):
        for r in self:
            if len(r.name)<5:
                raise exceptions.ValidationError("size of name must be up to 5")

    @api.onchange('user_id')
    def update(self):
      self.team_ids = None
      return {
            'warning': {
                'title': 'Responsible User Reset',
                'message': 'Please choose a new Team.',
            }
        }
   