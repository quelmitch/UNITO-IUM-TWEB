import re
import pandas as pd


# Summarizes null values in a DataFrame.
def summarize_nulls(df):
    return pd.DataFrame({
        'Null Count': df.isna().sum(),
        'Null Percentage': (df.isna().mean() * 100).round(4).astype(str) + '%'
    })


# Find matches in a column given patterns and length
def find_matching(df, column_name, keywords, min_length=None, max_length=None):
    # Compile the regex pattern (case-insensitive)
    pattern = re.compile(r'(?i)' + '|'.join(keywords))

    # Apply min_length and max_length filtering, if specified
    if min_length is not None:
        df = df[df[column_name].str.len() >= min_length]
    if max_length is not None:
        df = df[df[column_name].str.len() <= max_length]

    # Filter rows that match the pattern
    match = df[df[column_name].str.contains(pattern, na=False)]
    return match


# Return the season of a date
def get_specific_season(date):
    month = date.month
    day = date.day
    # Winter: December 21st to March 20th
    if (month == 12 and day >= 21) or (month == 1) or (month == 2) or (month == 3 and day <= 20):
        return 'Winter'
    # Spring: March 21st to June 20th
    if (month == 3 and day >= 21) or (month == 4) or (month == 5) or (month == 6 and day <= 20):
        return 'Spring'
    # Summer: June 21st to September 20th
    if (month == 6 and day >= 21) or (month == 7) or (month == 8) or (month == 9 and day <= 20):
        return 'Summer'
    # Autumn: September 21st to December 20th
    if (month == 9 and day >= 21) or (month == 10) or (month == 11) or (month == 12 and day <= 20):
        return 'Autumn'


# Made with generative AI. List of common phrases to indicate missing plot.
null_movie_description_keywords = [
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

self_actor_role_keywords = [
    "self",
    "himself",
    "herself",
    "itself",
    "self \\(archive footage\\)",
    "self \\(archive recording\\)",
    "self \\(uncredited\\)",
    "self \\(voice\\)"
]

special_oscar_awards = [
    "SPECIAL AWARD",
    "IRVING G. THALBERG MEMORIAL AWARD",
    "JEAN HERSHOLT HUMANITARIAN AWARD",
    "HONORARY AWARD",
    "SPECIAL FOREIGN LANGUAGE FILM AWARD",
    "HONORARY FOREIGN LANGUAGE FILM AWARD",
    "SPECIAL ACHIEVEMENT AWARD",
    "SPECIAL ACHIEVEMENT AWARD \\(Visual Effects\\)",
    "SPECIAL ACHIEVEMENT AWARD \\(Sound Effects\\)",
    "SPECIAL ACHIEVEMENT AWARD \\(Sound Effects Editing\\)",
    "SPECIAL ACHIEVEMENT AWARD \\(Sound Editing\\)",
    "GORDON E. SAWYER AWARD",
    "AWARD OF COMMENDATION"
]

season_colors = {
    'Winter': 'blue',
    'Spring': 'green',
    'Summer': 'orange',
    'Autumn': 'red'
}
