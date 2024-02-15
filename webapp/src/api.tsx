/**
 * IMPORTANT
 *
 * If your API calls aren't returning any results:
 *      1. Is your API definitely running?
 *      2. Can you make the API request in the browser (if its a GET) by loading the address directly?
 *      3. Check the address in package.json in the "proxy" setting. This needs to be the URL that your API runs on.
 *          If the port there is different, you need to update package.json and restart this webapp.
 */
const API_BASE = "/api";


/** Generic function to parse an API response as JSON */
const convertResponseToJson = (response: Response) => {
  if (response.status !== 200) {
    console.error(
      "There was a problem with the API response, status code:",
      response.status
    );
    return;
  }

  return response.json();
};

/** Generic error handler for API calls. */
const errorHandler = (err: string) => {
  console.log("Error after API call", err);
};

/**
 * API Call - Get all Movies
 *
 * fetch() is built into modern browsers, and lets you easily make API calls.
 * https://developers.google.com/web/updates/2015/03/introduction-to-fetch
 */
export function getAllMovies() {
  return fetch(`${API_BASE}/movies`)
    .then(convertResponseToJson)
    .catch(errorHandler);
}

/*export function DeleteMovie(id) {
  return fetch(`${API_BASE}/movies/${id}`)
    .then(convertResponseToJson)
    .catch(errorHandler);
}*/
export function getAllMoviesAscending() {
  return fetch(`${API_BASE}/movies`)
    .then(convertResponseToJson)
    .catch(errorHandler);
}
