terraform {

  required_version = ">= 1.0"

}

variable "project_name" {

  description = "Name of the application"

  type = string

  default = "nexora-digital-platform"

}

output "project_name" {

  value = var.project_name

}