# Provides configuration details for Terraform to manage Azure resources.
# We strongly recommend using the required_providers block to set the
# Azure Provider source and version being used
terraform {
  required_providers {
    azurerm = {
      source  = "hashicorp/azurerm"
      version = "~>3.0.0" # Pessimistic version constraint to ensure compatibility with AzureRM 3.x
    }
  }
}

# Configure the Microsoft Azure Provider
# This is Azure specific. These are feature flags
provider "azurerm" {
  features {}
}

# Create a resource group
# Create a resource of type and 
resource "azurerm_resource_group" "rg" {
  name     = "ecommerce-rg"
  location = "southcentralus"
  tags = {
    environment = "prod"
    source      = "terraform"
  }
}

# Create a virtual network within the resource group
resource "azurerm_virtual_network" "vnet" {
  name                = "ecommerce-vnet"
  resource_group_name = azurerm_resource_group.rg.name
  location            = azurerm_resource_group.rg.location
  address_space       = ["10.0.0.0/16"]
}