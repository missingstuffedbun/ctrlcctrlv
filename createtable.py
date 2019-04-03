from google.cloud import bigtable
from google.cloud.bigtable import column_family

project_id = 'thisisasimplenameasap'
instance_id = 'fortest'
table_id = 'table00'
column_family_id = 'cf'

client = bigtable.Client(project=project_id, admin=True)
instance = client.instance(instance_id)

print('Creating the {} table.'.format(table_id))
table = instance.table(table_id)

print('Creating column family cf1 with Max Version GC rule...')
max_versions_rule = column_family.MaxVersionsGCRule(2)
column_families = {column_family_id: max_versions_rule}
if not table.exists():
    table.create(column_families=column_families)
else:
    print("Table {} already exists.".format(table_id))
