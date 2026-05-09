# Task Orbit Web App

A basic task manager web app built with HTML, CSS, and JavaScript.

## Features

- Add a task
- Mark task as done
- Delete task
- Clear all completed tasks
- Persist tasks in browser local storage

## Project Files

- `index.html` - App structure
- `styles.css` - Styling, animations, and responsive layout
- `app.js` - Task logic and local storage

## Run Locally

### Option 1: Open directly

Open `index.html` in your browser.

### Option 2: Serve from terminal

If you have Node.js:

```bash
npx serve .
```

Then open the local URL shown in terminal.

## Deploy to Vercel

1. Push this folder to GitHub.
2. In Vercel, click **Add New Project** and import the repo.
3. Use these settings:
   - Framework Preset: `Other`
   - Root Directory: `./`
   - Build Command: leave empty
   - Output Directory: leave empty
4. Click **Deploy**.

Vercel will host this as a static site.

## Legacy Java Version

The original desktop Java Swing version is still in:

- `task.java`
- `taskmanager.java`
