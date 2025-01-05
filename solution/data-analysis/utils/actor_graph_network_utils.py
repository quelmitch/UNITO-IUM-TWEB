import networkx as nx

def network_graph(actors):
    # Creazione delle relazioni persona -> persona per ogni film
    relationships = actors.merge(actors, on='movie_id')
    relationships = relationships[relationships['name_x'] != relationships['name_y']]

    G = nx.Graph()
    for _, row in relationships.iterrows():
        G.add_edge(row['name_x'], row['name_y'])

    # Filter to keep only the top N nodes by degree
    degree_dict = dict(G.degree())
    top_n_nodes = sorted(degree_dict, key=degree_dict.get, reverse=True)[:1000]
    G = G.subgraph(top_n_nodes)

    # Calculate positions and centrality
    pos = nx.spring_layout(G, k=0.5, iterations=50)
    centrality = nx.degree_centrality(G)

    # Edge trace
    edge_x, edge_y = [], []
    for edge in G.edges():
        x0, y0 = pos[edge[0]]
        x1, y1 = pos[edge[1]]
        edge_x.extend([x0, x1, None])
        edge_y.extend([y0, y1, None])

    # Node trace
    node_x, node_y, node_sizes, node_text = [], [], [], []
    for node, (x, y) in pos.items():
        node_x.append(x)
        node_y.append(y)
        node_sizes.append(centrality[node] * 50 + 10)  # Adjust size based on centrality
        node_text.append(f"{node}<br>Connections: {int(centrality[node] * len(G.nodes()))}")

    return {'centrality': centrality, 'edges': (edge_x, edge_y), 'nodes': (node_x, node_y, node_sizes, node_text)}