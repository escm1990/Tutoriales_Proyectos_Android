package com.escm.lbn.mailer

data class Correo(val email:String = "DEFAULT",
                  val nombre:String = "DEFAULT_NAME",
                  val comentarios:String = "DEFAULT_COMMENT")