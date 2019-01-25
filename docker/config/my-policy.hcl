# Dev servers have version 2 of KV mounted by default, so will need these
# paths:
path "secret/data/*" {
  capabilities = ["create"]
}

path "secret/data/foo" {
  capabilities = ["read"]
}
