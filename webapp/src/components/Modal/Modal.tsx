import { FC, ReactElement } from "react";
import "./Modal.css";
import { url } from "inspector";

interface ModalProps {
    open: boolean;
    id: string;
    onClose: () => void;
    children: ReactElement;
    onDelete: (movieId: string) => void;
}

export default function Modal(props: ModalProps): ReturnType<FC> {
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


    const submitDelete = () => {
      const url  = 'http://localhost:8080/api/movies/'+`${props.id}`
      
        fetch(url, {
          method: 'DELETE',
        })
        .then(()=>{   props.onClose(); props.onDelete(props.id) ;/*Deletebyid(props.id);*/ })
        .catch(errorHandler);
      };
    return (
        <div className={`${"modal"} ${props.open ? "display-block" : "display-none"}`}>
            <div className="modal-main">
                <div className="modal-head">
                    <h1>Are you sure you want to delete?</h1>
                </div>
                <div className="modal-body">
                    {props.children}
                </div>
                <div className="btn-container">
                    <button type="button" className="btn-confirm" onClick={submitDelete}>Yes</button>
                    <button type="button" className="btn-close" onClick={props.onClose}>No</button>
                </div>
            </div>
        </div>
    );
}