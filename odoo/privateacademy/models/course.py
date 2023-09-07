# -*- coding: utf-8 -*-

from odoo import models,fields
import logging

_logger = logging.getLogger(__name__)

class Course(models.Model):
    _inherit = "openacademy.course"

    event_ids = fields.Many2one('calendar.event', ondelete='cascade', string="Remise de diplome")

    def pay(self):
        journal = self.env['account.move'].with_context(
            default_move_type='out_invoice')._get_default_journal()
        for current in self:
            self.env["account.move"].create(
                {
                    "partner_id": self.env.uid,
                    "move_type": "out_invoice",
                    "journal_id": journal.id,
                    "invoice_line_ids": [
                        (
                            0,
                            0,
                            {
                                "name": current.name,
                                "quantity": 1.0,
                                "price_unit": 500,
                            },
                        ),
                        (
                            0,
                            0,
                            {
                                "name": "Administrative fees",
                                "quantity": 1.0,
                                "price_unit": 100.0,
                            },
                        ),
                    ],
                }
            )