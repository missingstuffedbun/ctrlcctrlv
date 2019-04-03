import datetime
import apache_beam as beam
from apache_beam.options import PipelineOptions, GoogleCloudOptions, StandardOptions
from google.cloud.bigtable import row


project_id = 'thisisasimplenameasap'
job_id = 'job00'
bucket_id = ''

options = PipelineOptions()
google_cloud_options = options.view_as(GoogleCloudOptions)
google_cloud_options.project = project_id
google_cloud_options.job_name = job_id
google_cloud_options.staging_location = bucket_id
google_cloud_options.temp_location = bucket_id
options.view_as(StandardOptions).runner = 'DataflowRunner'


class FormatAsRow(beam.DoFn):
    def process(self, element):
        cf = 'cf'
        column_names = ['prediction', 'time', 'prob_0', 'prob_1']
        direct_row = row.DirectRow(row_key=element['event_id'])
        for name in column_names:
            direct_row.set_cell(column_family_id=cf,
                                column=name,
                                value=element[name],
                                timestamp=datetime.datetime.now())
        yield direct_row


with beam.Pipeline(options=options) as p:
    _ = p | beam.io.ReadFromText(bucket_id+'kinglear.txt')\
        | 'ExtractWords' >> beam.FlatMap(lambda x: re.findall(r'[A-Za-z\']+', x))\
        | beam.combiners.Count.PerElement()\
        | beam.Map(lambda word_count: '%s: %s' % (word_count[0], word_count[1]))\
        | beam.io.WriteToText(bucket_id+'counts.txt')
    result = p.run()

