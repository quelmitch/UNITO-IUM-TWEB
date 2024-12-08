import re

def find_matching(df, column_name, keywords):
    pattern = re.compile(r'(?i)' + '|'.join(keywords))  # (?i) case-insensitive regex
    filtered_df = df[df[column_name].str.len() <= 30]
    match = filtered_df[filtered_df[column_name].str.contains(pattern, na=False)]
    return match

# Made with generative AI. List of common phrases to indicate missing plot.
null_description_keywords = [
    r'plot unavailable', r'plot unknown', r'plot not found', r'plot missing',
    r'plot (?:\S+\s+){0,3}unavailable', r'plot (?:\S+\s+){0,3}unknown', r'plot (?:\S+\s+){0,3}not found',
    r'plot (?:\S+\s+){0,3}missing',

    r'synopsis unavailable', r'synopsis unknown', r'synopsis not found', r'synopsis missing',
    r'synopsis (?:\S+\s+){0,3}unavailable', r'synopsis (?:\S+\s+){0,3}unknown', r'synopsis (?:\S+\s+){0,3}not found',
    r'synopsis (?:\S+\s+){0,3}missing',

    r'summary unavailable', r'summary unknown', r'summary not found', r'summary missing',
    r'summary (?:\S+\s+){0,3}unavailable', r'summary (?:\S+\s+){0,3}unknown', r'summary (?:\S+\s+){0,3}not found',
    r'summary (?:\S+\s+){0,3}missing',

    r'overview unavailable', r'overview unknown', r'overview not found', r'overview missing',
    r'overview (?:\S+\s+){0,3}unavailable', r'overview (?:\S+\s+){0,3}unknown', r'overview (?:\S+\s+){0,3}not found',
    r'overview (?:\S+\s+){0,3}missing',

    r'storyline unavailable', r'storyline unknown', r'storyline not found', r'storyline missing',
    r'storyline (?:\S+\s+){0,3}unavailable', r'storyline (?:\S+\s+){0,3}unknown', r'storyline (?:\S+\s+){0,3}not found',
    r'storyline (?:\S+\s+){0,3}missing',

    r'description unavailable', r'description unknown', r'description not found', r'description missing',
    r'description (?:\S+\s+){0,3}unavailable', r'description (?:\S+\s+){0,3}unknown',
    r'description (?:\S+\s+){0,3}not found', r'description (?:\S+\s+){0,3}missing',

    r'details unavailable', r'details unknown', r'details not found', r'details missing',
    r'details (?:\S+\s+){0,3}unavailable', r'details (?:\S+\s+){0,3}unknown', r'details (?:\S+\s+){0,3}not found',
    r'details (?:\S+\s+){0,3}missing'
]