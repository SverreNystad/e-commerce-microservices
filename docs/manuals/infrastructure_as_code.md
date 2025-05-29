# Infrastructure as Code (IaC)

In this project I use Terraform to manage the infrastructure for the e-commerce application. The IaC approach allows for consistent and repeatable deployments, making it easier to manage the infrastructure across different environments.

Specifically, I use Terraform with Azure


## Usage
To use the Terraform scripts, follow these steps:
1. Install Terraform on your local machine.


## Commands
Navigate to the directory containing the Terraform scripts and run the following commands:


To initialize the Terraform working directory, run:
```bash
terraform init
```

To format the Terraform code, run:
```bash
terraform fmt
```

To validate the Terraform code, run:
```bash
terraform validate
```

To check the execution plan and see what changes will be made to the infrastructure, run:
```bash
terraform plan
```
This will show you a preview of the changes that will be applied.

To apply the changes and create/update the infrastructure, run:
```bash
terraform apply
```


In case you want to destroy the infrastructure, you can run:
```bash
terraform destroy
```