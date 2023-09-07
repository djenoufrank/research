from odoo import models,fields


class Tag(models.Model):
    _name='todo.task.tag'
    _description = 'To-do Tag'
    name = fields.Char('Name',help="gives name's tag",required=True)
    task_ids = fields.Many2many('todo.task', string='Taks')
    _sql_constraints = [('name_unique','UNIQUE(name)',"The name be unique"),]
